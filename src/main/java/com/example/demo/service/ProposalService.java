package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.NomineeDto;
import com.example.demo.dto.ProposalDto;
import com.example.demo.entity.Proposal;
import com.example.demo.response.Response;

public interface ProposalService {

	public Proposal addProposal(ProposalDto proposalDto);

	public String deleteproposal(Integer proposalId);

	public String updatepropsal(Integer proposalId, ProposalDto proposalDto);
	
	public List<ProposalDto>getAllProposalWithNominee();
	
	public Response updateNominee(Integer nomineeId, NomineeDto nomineeDto);
	

}
