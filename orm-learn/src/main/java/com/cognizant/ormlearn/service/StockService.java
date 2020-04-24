package com.cognizant.ormlearn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	@Transactional
	public List<Stock> getAllStock() {
		return stockRepository.findAll();
	}

	@Transactional
	public List<Stock> getDate() {
		return stockRepository.findByDate();
	}

	@Transactional
	public List<Stock> getGoogle() {
		return stockRepository.findByGoogle();
	}

	@Transactional
	public List<Stock> getMax() {
		return stockRepository.findMax();
	}

	@Transactional
	public List<Stock> getLowest() {
		return stockRepository.findLowest();
	}

}