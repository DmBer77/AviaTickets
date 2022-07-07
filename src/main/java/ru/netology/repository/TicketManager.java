package ru.netology.repository;

import ru.netology.ticket.Ticket;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {

    private final TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void addNewTicket(Ticket newTicket) {
        repository.addNewTicket(newTicket);
    }

    public void addNewTicketButCheckIfSimilarTicketAlreadyAdded(Ticket newTicket) {
        repository.addNewTicketButCheckIfSimilarTicketAlreadyAdded(newTicket);
    }

    public void removeTicketById(int id) {
        repository.removeTicketById(id);
    }

    public Ticket[] getSavedTickets() {
        return repository.getSavedTickets();
    }

    public Ticket[] findAll(String departureAirport, String arrivalAirport) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.getSavedTickets()) {
            if (matches(ticket, departureAirport, arrivalAirport)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String departureAirport, String arrivalAirport) {

        if (ticket.getDepartureAirport().contains(departureAirport)) {
            return ticket.getArrivalAirport().contains(arrivalAirport);
        }

        return false;
    }

}
