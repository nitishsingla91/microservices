package com.nagarro.nes.enums;

public enum DeliveryState {

	PACKAGING_IN_PROGRESS, PREPARED, SHIPPED, OUT_FOR_DELIVERY, DELIVERED, UNDELIVERED;

	public static boolean contains(final String state) {
		for (final DeliveryState c : DeliveryState.values()) {
			if (c.name().equals(state)) {
				return true;
			}
		}
		return false;
	}

}
