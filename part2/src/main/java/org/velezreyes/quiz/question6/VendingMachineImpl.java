package org.velezreyes.quiz.question6;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements  VendingMachine{
    private static VendingMachineImpl instance;
    private int insertedQuarters;
    private Map<String, Drink> drinks;

    public VendingMachineImpl() {
        this.insertedQuarters = 0;
        this.drinks = new HashMap<>();
        initializeDrinks();
    }

    public static VendingMachineImpl getInstance() {
        if (instance == null) {
            instance = new VendingMachineImpl();
        }
        return instance;
    }

    private void initializeDrinks() {
        // Add drinks to the vending machine
        drinks.put("ScottCola", new DrinkImpl("ScottCola", true, 75));
        drinks.put("KarenTea", new DrinkImpl("KarenTea", false, 100));
    }

    @Override
    public void insertQuarter() {
        insertedQuarters+=25;
    }

    @Override
    public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
        if (!drinks.containsKey(name)) {
            throw new UnknownDrinkException();
        }

        Drink selectedDrink = drinks.get(name);

        if (insertedQuarters < selectedDrink.getPriceInCents()) {
            throw new NotEnoughMoneyException();
        }

        insertedQuarters -= selectedDrink.getPriceInCents();
        return selectedDrink;
    }

    public class DrinkImpl implements Drink {
        private String name;
        private boolean isFizzy;
        private int priceInCents;

        public DrinkImpl(String name, boolean isFizzy, int priceInCents) {
            this.name = name;
            this.isFizzy = isFizzy;
            this.priceInCents = priceInCents;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean isFizzy() {
            return isFizzy;
        }
        @Override
        public int getPriceInCents() {
            return priceInCents;
        }
    }
}
