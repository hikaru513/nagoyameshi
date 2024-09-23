package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.form.ReservationRegisterForm;
import com.example.nagoyameshi.repository.ReservationRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Transactional
	public void create(ReservationRegisterForm ReservationRegisterForm) {
		Reservation reservation = new Reservation();

		reservation.setReservationTime(ReservationRegisterForm.getReservationTime());
		reservation.setNumberOfPeople(ReservationRegisterForm.getNumberOfPeople());

		reservationRepository.save(reservation);

	}
}