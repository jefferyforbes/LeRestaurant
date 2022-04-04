package com.jeffery.lerestaurant.domain

enum class OrderState {
    // The state management is because we don't have the current cart api so we need to manage
    // the order state in the interim of completing the transaction and the order itself before
    // finally closing the state to RECEIVED

    PENDING, // Once the user creates the order but has confirmed the service
    CONFIRMED, // Order placed and awaiting food
    COMPLETED, // Transaction or Payment received
    RECEIVED // Transaction and Payment received and the food has been delivered
}