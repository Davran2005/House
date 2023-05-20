package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.exception.MyException;
import peaksoft.model.Booking;
import peaksoft.repository.BookingRep;

import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class BookingRepImpl implements BookingRep {
    private final EntityManager entityManager;
    @Override
    public void saveBooking(Booking booking) {

        entityManager.persist(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        try {
            Booking booking = entityManager.find(Booking.class, id);
            if (booking.getId().equals(id)) {
                return booking;
            } else {
                throw new MyException("Booking of this" + id + "was not found");
            }
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Booking> getAllBooking() {
        try {
            List<Booking> bookings = entityManager.createQuery("select u from Booking u", Booking.class).getResultList();
            if (bookings.isEmpty()) {
                throw new MyException("While there is no agent");
            } else return bookings;
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


        @Override
    public void updateBookingById(Long id, Booking booking) {
            try {
                Booking booking1 = entityManager.find(Booking.class, id);
                boolean isFalse = true;
                if (booking1.getId().equals(id)) {
                   booking1.setCustomer(booking.getCustomer());
                   booking1.setHouse(booking.getHouse());
                   entityManager.merge(booking1);
                    isFalse = false;
                } else {
                    throw new MyException("Booking of this" + id + "was not found");

                }
            } catch (MyException e) {
                System.err.println(e.getMessage());
            }
        }

    @Override
    public void deleteBookingById(Long id) {
        try {
            Booking booking = entityManager.find(Booking.class, id);
            if (booking.getId().equals(id)) {
                entityManager.remove(booking);
            } else {
                throw new MyException("Booking of this" + id + "was not found");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }
}
