package com.alisovenko.cracking.the.interview._8_oop;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author alisovenko 06.10.14
 */
public class ParkingLotExample {
    abstract class Vehicle {
        int width;
        int length;
    }
    class Car extends Vehicle {

    }
    class Motocycle extends Vehicle {

    }
    class Bus extends Vehicle {

    }

    class ParkingController {
        Parking parking;
        public void register (Vehicle v) {

        }

        public void deRegister(Vehicle vehicle) {

        }
    }

    class RegistrationInfo {
        Date date;
        Vehicle vehicle;
    }

    class Parking {
        int floors;
        Set<Slot> allSlots;
        Map<Slot, RegistrationInfo> registry;

        public Slot nextAvailableSlotFor(Vehicle vehicle) {
            return null;
        }

        public boolean occupy(Slot slot, Vehicle vehicle) {
            return true;
        }
    }
    class Slot {
        int floor;
        int index;
        SlotType type;
    }
    enum SlotType {
        SMALL(Motocycle.class), MEDIUM(Car.class, Motocycle.class), LARGE(Car.class, Bus.class);
        Class<? extends Vehicle>[] vehicles;

        SlotType(Class<? extends Vehicle>... busClass) {
            this.vehicles = busClass;
        }

        boolean isApplicableFor(Vehicle vehicle) {
            return vehicles[0] == vehicle.getClass();
        }
    }
}
