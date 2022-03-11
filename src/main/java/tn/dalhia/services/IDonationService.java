package tn.dalhia.services;

import org.springframework.stereotype.Service;
import tn.dalhia.entities.Donation;

import java.util.List;


public interface IDonationService {

    List<Donation> getAll();
}
