package com.mate.service;

import com.mate.dao.CinemaHallDao;
import com.mate.lib.Inject;
import com.mate.lib.Service;
import com.mate.model.CinemaHall;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
