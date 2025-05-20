package com.example.demo.service;

import com.example.demo.dto.ProposalDto;
import com.example.demo.entity.Proposal;

public interface ProposalService {

	public Proposal addProposal(ProposalDto proposalDto);

	public String deleteproposal(Integer proposalId);

	public String updatepropsal(Integer proposalId, ProposalDto proposalDto);

}
