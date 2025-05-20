package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

	public Optional<Proposal> findByProposalIdAndStatus(Integer proposalId, Character status);

}
