package com.jeffery.lerestaurant.data.repositories

import com.jeffery.lerestaurant.data.entities.MenuItem

class MenuItemDatasource {

    // Base data for the user to have items to order.

    private val doughBalls = MenuItem(
        course = STARTER,
        name = "Dough balls",
        itemCount = 0,
        price = 4.99,
        stock = 51
    )

    private val garlicPizzaBread = MenuItem(
        course = STARTER,
        name = "Garlic Pizza Bread",
        itemCount = 0,
        price = 4.59,
        stock = 50
    )

    private val garlicPizzaBreadWithMozzarella = MenuItem(
        course = STARTER,
        name = "Garlic Pizza Bread with Mozzarella",
        itemCount = 0,
        price = 4.99,
        stock = 85
    )

    private val garlicPizzaBreadWithVeganMozzarella = MenuItem(
        course = STARTER,
        name = "Garlic Pizza Bread with Vegan Mozzarella",
        itemCount = 0,
        price = 4.99,
        stock = 80
    )

    private val meatBalls = MenuItem(
        course = STARTER,
        name = "Meatballs",
        itemCount = 0,
        price = 5.50,
        stock = 32
    )

    private val mozzarellaSticks = MenuItem(
        course = STARTER,
        name = "Mozzarella Sticks",
        itemCount = 0,
        price = 3.99,
        stock = 150
    )

    private val baconAndCheesePotatoSkins = MenuItem(
        course = STARTER,
        name = "Bacon & Cheese Potato Skins",
        itemCount = 0,
        price = 5.50,
        stock = 55
    )

    private val cheeseAndChiveFullyLoadedPotatoSkins = MenuItem(
        course = STARTER,
        name = "Cheese & Chive Fully Loaded Potato Skins",
        itemCount = 0,
        price = 5.25,
        stock = 56
    )

    private val mozzarellaAndTomatoSalad = MenuItem(
        course = STARTER,
        name = "Mozzarella & Tomato Salad",
        itemCount = 0,
        price = 4.50,
        stock = 90
    )

    private val bbqChickenWings = MenuItem(
        course = STARTER,
        name = "BBQ Chicken Wings",
        itemCount = 0,
        price = 5.50,
        stock = 50
    )

    private val southernFriedChickenWithBBQSauce = MenuItem(
        course = STARTER,
        name = "Southern Fried Chicken with BBQ Sauce",
        itemCount = 0,
        price = 5.50,
        stock = 103
    )

    private val stickyHotChickenWings = MenuItem(
        course = STARTER,
        name = "Sticky Hot Chicken Wings",
        itemCount = 0,
        price = 5.50,
        stock = 109
    )

    private val southernFriedChickenWithHotSauce = MenuItem(
        course = STARTER,
        name = "Southern Fried Chicken with Hot Sauce",
        itemCount = 0,
        price = 5.50,
        stock = 98
    )

    private val classicBeefBurger = MenuItem(
        course = MAIN,
        name = "Classic Beef Burger",
        itemCount = 0,
        price = 9.99,
        stock = 45
    )

    private val doubleBaconCheeseBurger = MenuItem(
        course = MAIN,
        name = "Double Bacon Cheese Burger",
        itemCount = 0,
        price = 10.99,
        stock = 45
    )

    private val classicCheeseBurger = MenuItem(
        course = MAIN,
        name = "Classic Cheese Burger",
        itemCount = 0,
        price = 8.99,
        stock = 45
    )

    private val chickenBLTBurger = MenuItem(
        course = MAIN,
        name = "Chicken BLT Burger",
        itemCount = 0,
        price = 9.99,
        stock = 45
    )

    private val veganBurger = MenuItem(
        course = MAIN,
        name = "Vegan Burger",
        itemCount = 0,
        price = 8.99,
        stock = 35
    )

    private val theHouseSpecialBurger = MenuItem(
        course = MAIN,
        name = "The House Special Burger",
        itemCount = 0,
        price = 11.50,
        stock = 69
    )

    private val classicChickenBurger = MenuItem(
        course = MAIN,
        name = "Classic Chicken Burger",
        itemCount = 0,
        price = 8.99,
        stock = 77
    )

    private val crispyBBQChickenBurger = MenuItem(
        course = MAIN,
        name = "Crispy BBQ Chicken Burger",
        itemCount = 0,
        price = 9.59,
        stock = 35
    )

    private val beefLasagne = MenuItem(
        course = MAIN,
        name = "Beef Lasagne",
        itemCount = 0,
        price = 10.50,
        stock = 15
    )

    private val meatballsWithSpaghetti = MenuItem(
        course = MAIN,
        name = "Meatballs with Spaghetti",
        itemCount = 0,
        price = 10.99,
        stock = 25
    )

    private val spaghettiCarbonara = MenuItem(
        course = MAIN,
        name = "Spaghetti Carbonara",
        itemCount = 0,
        price = 9.99,
        stock = 45
    )

    private val macNCheese = MenuItem(
        course = MAIN,
        name = "Mac 'N' Cheese",
        itemCount = 0,
        price = 9.99,
        stock = 50
    )

    private val ultimateMacNCheeseWithBacon = MenuItem(
        course = MAIN,
        name = "Ultimate Mac 'N' Cheese with Bacon",
        itemCount = 0,
        price = 10.59,
        stock = 25
    )

    private val spaghettiBolognese = MenuItem(
        course = MAIN,
        name = "Spaghetti Bolognese",
        itemCount = 0,
        price = 9.99,
        stock = 45
    )

    private val spaghettiArrabbiata = MenuItem(
        course = MAIN,
        name = "Spaghetti Arrabbiata",
        itemCount = 0,
        price = 10.50,
        stock = 23
    )

    private val chickenAndPrawnAlfredoWithSpaghetti = MenuItem(
        course = MAIN,
        name = "Chicken & Prawn Alfredo with Spaghetti",
        itemCount = 0,
        price = 12.00,
        stock = 12
    )

    private val tenInchePepperoniPizza = MenuItem(
        course = MAIN,
        name = "10'' Pepperoni Pizza",
        itemCount = 0,
        price = 9.50,
        stock = 23
    )

    private val tenIncheBBQChickenPizza = MenuItem(
        course = MAIN,
        name = "10'' BBQ Chicken Pizza",
        itemCount = 0,
        price = 10.00,
        stock = 34
    )

    private val tenIncheHawaiianPizza = MenuItem(
        course = MAIN,
        name = "10'' Hawaiian Pizza",
        itemCount = 0,
        price = 9.50,
        stock = 32
    )

    private val tenIncheVeganMargheritaPizza = MenuItem(
        course = MAIN,
        name = "10'' Vegan Margherita Pizza",
        itemCount = 0,
        price = 7.99,
        stock = 22
    )

    private val tenIncheCajunChickenPizza = MenuItem(
        course = MAIN,
        name = "10'' Cajun Chicken Pizza",
        itemCount = 0,
        price = 8.99,
        stock = 31
    )

    private val tenIncheMargheritaPizza = MenuItem(
        course = MAIN,
        name = "10'' Margherita Pizza",
        itemCount = 0,
        price = 7.99,
        stock = 45
    )

    private val fifteenIncheMargheritaPizza = MenuItem(
        course = MAIN,
        name = "15'' Margherita Pizza",
        itemCount = 0,
        price = 12.00,
        stock = 54
    )

    private val fifteenIncheCajunChickenPizza = MenuItem(
        course = MAIN,
        name = "15'' Cajun Chicken Pizza",
        itemCount = 0,
        price = 13.50,
        stock = 12
    )

    private val fifteenInchePepperoniPizza = MenuItem(
        course = MAIN,
        name = "15'' Pepperoni Pizza",
        itemCount = 0,
        price = 13.50,
        stock = 12
    )

    private val fifteenIncheBBQChickenPizza = MenuItem(
        course = MAIN,
        name = "15'' BBQ Chicken Pizza",
        itemCount = 0,
        price = 13.50,
        stock = 32
    )

    private val fifteenIncheHawaiianPizza = MenuItem(
        course = MAIN,
        name = "15'' Hawaiian Pizza",
        itemCount = 0,
        price = 12.50,
        stock = 45
    )

    private val fifteenIncheVeganMargheritaPizza = MenuItem(
        course = MAIN,
        name = "15'' Vegan Margherita Pizza",
        itemCount = 0,
        price = 11.50,
        stock = 66
    )

    private val sweetPotatoFries = MenuItem(
        course = SIDES,
        name = "Sweet Potato Fries",
        itemCount = 0,
        price = 2.99,
        stock = 34
    )

    private val onionRings = MenuItem(
        course = SIDES,
        name = "Onion Rings",
        itemCount = 0,
        price = 3.50,
        stock = 23
    )

    private val greens = MenuItem(
        course = SIDES,
        name = "Greens",
        itemCount = 0,
        price = 2.99,
        stock = 12
    )

    private val fries = MenuItem(
        course = SIDES,
        name = "Fries",
        itemCount = 0,
        price = 2.59,
        stock = 110
    )

    private val cornOnTheCob = MenuItem(
        course = SIDES,
        name = "Corn on the Cob",
        itemCount = 0,
        price = 2.50,
        stock = 45
    )

    private val sideSalad = MenuItem(
        course = SIDES,
        name = "Side Salad",
        itemCount = 0,
        price = 2.99,
        stock = 2
    )

    private val coleslaw = MenuItem(
        course = SIDES,
        name = "Coleslaw",
        itemCount = 0,
        price = 2.50,
        stock = 32
    )

    private val beans = MenuItem(
        course = SIDES,
        name = "Beans",
        itemCount = 0,
        price = 1.99,
        stock = 15
    )

    private val tea = MenuItem(
        course = DRINKS,
        name = "Tea",
        itemCount = 0,
        price = 1.50,
        stock = 99
    )

    private val coffee = MenuItem(
        course = DRINKS,
        name = "Coffee",
        itemCount = 0,
        price = 1.65,
        stock = 205
    )

    private val coke = MenuItem(
        course = DRINKS,
        name = "Coke",
        itemCount = 0,
        price = 1.50,
        stock = 108
    )

    private val fanta = MenuItem(
        course = DRINKS,
        name = "Fanta",
        itemCount = 0,
        price = 1.50,
        stock = 45
    )

    private val sprite = MenuItem(
        course = DRINKS,
        name = "Sprite",
        itemCount = 0,
        price = 1.50,
        stock = 54
    )

    private val orangeJuice = MenuItem(
        course = DRINKS,
        name = "Orange Juice",
        itemCount = 0,
        price = 2.25,
        stock = 60
    )

    private val water = MenuItem(
        course = DRINKS,
        name = "Water",
        itemCount = 0,
        price = 1.25,
        stock = 120
    )

    val restaurantMenuItems = mutableListOf(
        doughBalls,
        garlicPizzaBread,
        garlicPizzaBreadWithMozzarella,
        garlicPizzaBreadWithVeganMozzarella,
        meatBalls,
        mozzarellaSticks,
        baconAndCheesePotatoSkins,
        cheeseAndChiveFullyLoadedPotatoSkins,
        mozzarellaAndTomatoSalad,
        bbqChickenWings,
        southernFriedChickenWithBBQSauce,
        stickyHotChickenWings,
        southernFriedChickenWithHotSauce,
        classicBeefBurger,
        doubleBaconCheeseBurger,
        classicCheeseBurger,
        chickenBLTBurger,
        veganBurger,
        theHouseSpecialBurger,
        classicChickenBurger,
        crispyBBQChickenBurger,
        beefLasagne,
        meatballsWithSpaghetti,
        spaghettiCarbonara,
        macNCheese,
        ultimateMacNCheeseWithBacon,
        spaghettiBolognese,
        spaghettiArrabbiata,
        chickenAndPrawnAlfredoWithSpaghetti,
        tenInchePepperoniPizza,
        tenIncheBBQChickenPizza,
        tenIncheHawaiianPizza,
        tenIncheVeganMargheritaPizza,
        tenIncheCajunChickenPizza,
        tenIncheMargheritaPizza,
        fifteenIncheMargheritaPizza,
        fifteenIncheCajunChickenPizza,
        fifteenInchePepperoniPizza,
        fifteenIncheBBQChickenPizza,
        fifteenIncheHawaiianPizza,
        fifteenIncheVeganMargheritaPizza,
        sweetPotatoFries,
        onionRings,
        greens,
        fries,
        cornOnTheCob,
        sideSalad,
        coleslaw,
        beans,
        tea,
        coffee,
        coke,
        fanta,
        sprite,
        orangeJuice,
        water
    )

    companion object {
        const val STARTER = "Starter"
        const val MAIN = "Main"
        const val SIDES = "Sides"
        const val DRINKS = "Drinks"
    }
}