package ru.netology.repository;

import ru.netology.ticket.Ticket;

public class TicketRepository {

    private Ticket[] items = new Ticket[0];

    public void addNewTicket(Ticket newTicket) {
        Ticket[] tmp = new Ticket[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = newTicket;
        items = tmp;
    }

    public void addNewTicketButCheckIfSimilarTicketAlreadyAdded(Ticket newTicket) {
        Ticket draft_1 = findById(newTicket.getId());
        if (draft_1 != null) {
            throw new AlreadyExistsException(
                    "Element with id: " + newTicket.getId() + " already exists"
            );
        }
        Ticket[] tmp = new Ticket[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = newTicket;
        items = tmp;
    }

    public void removeTicketById(int id) {
        Ticket draft_2 = findById(id);
        if (draft_2 == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        Ticket[] tmp = new Ticket[items.length - 1];
        int copyToIndex = 0;
        for (Ticket item : items) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        items = tmp;
    }

    public Ticket findById(int id) {
        for (Ticket item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Ticket[] getSavedTickets() {
        return items;
    }

}
