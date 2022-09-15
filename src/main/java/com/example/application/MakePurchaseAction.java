package com.example.application;

import com.example.api.PurchaseDTO;
import com.example.api.purchase.CreditCardDTO;
import com.example.api.purchase.ItemDTO;
import com.example.domain.*;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Orchestrates {@link Purchase} creation.
 */
public interface MakePurchaseAction {
    /**
     * @param creditCard credit card for purchase
     * @param items      list of items to buy
     * @return a purchase information
     */
    PurchaseDTO execute(CreditCardDTO creditCard, List<ItemDTO> items);

    @Component
    class MakePurchaseActionImpl implements MakePurchaseAction {

        private final CreditCardRepository creditCardRepository;
        private final ItemRepository itemRepository;
        private final PurchaseService purchaseService;

        private final ChargeRepository chargeRepository;

        /**
         * @param creditCardRepository repository for credit cards
         * @param itemRepository repository for items
         * @param purchaseService purchase service
         * @param chargeRepository repository for charges
         */
        @Autowired
        MakePurchaseActionImpl(CreditCardRepository creditCardRepository,
                                      ItemRepository itemRepository, PurchaseService purchaseService,
                                      ChargeRepository chargeRepository) {
            this.creditCardRepository = creditCardRepository;
            this.itemRepository = itemRepository;
            this.purchaseService = purchaseService;
            this.chargeRepository = chargeRepository;
        }

        @Override
        @Transactional
        public PurchaseDTO execute(CreditCardDTO creditCardDTO, List<ItemDTO> itemDTOS) {
            String cardNumber = creditCardDTO.getCardNumber();
            if (cardNumber == null || cardNumber.isEmpty()) {
                throw new InvalidCreditCardException(cardNumber);
            }
            Option<CreditCard> creditCardOption = creditCardRepository.get(cardNumber);
            if (creditCardOption.isEmpty()) {
                throw new InvalidCreditCardException(cardNumber);
            }
            Map<Item, Integer> itemsToQuantity = itemDTOS.groupBy(ItemDTO::getId)
                    .mapKeys(itemRepository::get)
                    .mapValues(List::size);
            Purchase purchase = purchaseService.makePurchase(creditCardOption.get(), itemsToQuantity);
            chargeRepository.save(purchase.getCharge());

            return PurchaseDTO.from(purchase);
        }
    }
}
