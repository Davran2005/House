package peaksoft.repository;

import peaksoft.model.Booking;

import java.util.List;

public interface BookingRep {
    void saveBooking(Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getAllBooking();
    void updateBookingById(Long id, Booking booking);
    void deleteBookingById(Long id);
}
