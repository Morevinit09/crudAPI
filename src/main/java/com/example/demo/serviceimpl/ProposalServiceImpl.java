package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NomineeDto;
import com.example.demo.dto.ProposalDto;
import com.example.demo.entity.Nominee;
import com.example.demo.entity.Proposal;
import com.example.demo.repository.NomineeRepository;
import com.example.demo.repository.ProposalRepository;
import com.example.demo.response.Response;
import com.example.demo.service.ProposalService;

@Service
public class ProposalServiceImpl implements ProposalService {

	private static final Response Response = null;

	@Autowired
	private ProposalRepository proposalRepository;

	@Autowired
	private NomineeRepository nomineeRepository;

	@Override
	public Proposal addProposal(ProposalDto proposalDto) {
		Proposal newProposal = new Proposal();

		newProposal.setProposalName(proposalDto.getProposalName());
		newProposal.setProposalAge(proposalDto.getProposalAge());
		newProposal.setProposalMobileNumber(proposalDto.getProposalMobileNumber());
		newProposal.setProposalDateOfBirth(proposalDto.getProposalDateOfBirth());
		newProposal.setStatus('Y');

		List<NomineeDto> nomineeDtos = proposalDto.getNomineeDtos();

		List<Nominee> nomineeslist = new ArrayList<>();

		for (NomineeDto nomineeDto : nomineeDtos) {
			Nominee nominee = new Nominee();

			nominee.setNomineeName(nomineeDto.getNomineeName());
			nominee.setNomineeMobileNumber(nomineeDto.getNomineeMobileNumber());
			nominee.setNomineeDateOfBirth(nomineeDto.getNomineeDateOfBirth());
			nominee.setStatus('Y');
			nominee.setNomineeAge(nomineeDto.getNomineeAge());
			nominee.setProposal(newProposal);

			nomineeslist.add(nominee);
		}

		newProposal.setNominees(nomineeslist);

		Proposal prosalAndNominee = proposalRepository.save(newProposal);

		return prosalAndNominee;
	}

	@Override
	public String deleteproposal(Integer proposalId) {
		Optional<Proposal> byId = proposalRepository.findById(proposalId);
		if (byId.isPresent()) {
			Proposal proposal = byId.get();

			proposal.setStatus('N');

			List<Nominee> nominees = proposal.getNominees();

			for (Nominee nominee : nominees) {

				nominee.setStatus('N');

				nomineeRepository.save(nominee);

			}

			proposalRepository.save(proposal);

		}
		return "delete Success !!!";
	}

	@Override
	public String updatepropsal(Integer proposalId, ProposalDto proposalDto) {

		Optional<Proposal> byProposalIdAndStatus = proposalRepository.findByProposalIdAndStatus(proposalId, 'Y');
		if (byProposalIdAndStatus.isPresent()) {

			Proposal proposal = byProposalIdAndStatus.get();
			proposal.setProposalName(proposalDto.getProposalName());
			proposal.setProposalMobileNumber(proposalDto.getProposalMobileNumber());
			proposal.setProposalAge(proposalDto.getProposalAge());
			proposal.setProposalDateOfBirth(proposalDto.getProposalDateOfBirth());

			proposalRepository.save(proposal);
		} else {
			throw new IllegalArgumentException("Proposal not present");
		}

		return "Update Success!!";
	}
	
	@Override
	public Response updateNominee(Integer nomineeId, NomineeDto nomineeDto) {
		Optional<Nominee> byNomineeIdAndStatus = nomineeRepository.findByNomineeIdAndStatus(nomineeId,'Y');
		
		if (byNomineeIdAndStatus.isPresent()) {
			Nominee nominee = byNomineeIdAndStatus.get();
			nominee.setNomineeName(nomineeDto.getNomineeName());
			nominee.setNomineeDateOfBirth(nominee.getNomineeDateOfBirth());
			nominee.setNomineeMobileNumber(nominee.getNomineeMobileNumber());
			nominee.setNomineeAge(nominee.getNomineeAge());
			
			nomineeRepository.save(nominee);
			
		}
		return Response; 
		
	}
	

	@Override
	public List<ProposalDto> getAllProposalWithNominee() {

		List<Proposal> mainEntity = proposalRepository.findAll();

		List<ProposalDto> propssalDto = new ArrayList<>();

		

		for (Proposal proposal : mainEntity) {

			ProposalDto dto = new ProposalDto();

			dto.setProposalName(proposal.getProposalName());
			dto.setProposalMobileNumber(proposal.getProposalMobileNumber());
			dto.setProposalAge(proposal.getProposalAge());
			dto.setProposalDateOfBirth(proposal.getProposalDateOfBirth());

			List<Nominee> nominees = proposal.getNominees();
			List<NomineeDto> nomineeDtos = new ArrayList<>();

			for (Nominee nominee : nominees) {

				NomineeDto nomineeDtoz = new NomineeDto();

				nomineeDtoz.setNomineeName(nominee.getNomineeName());
				nomineeDtoz.setNomineeDateOfBirth(nominee.getNomineeDateOfBirth());
				nomineeDtoz.setNomineeAge(nominee.getNomineeAge());
				nomineeDtoz.setNomineeMobileNumber(nominee.getNomineeMobileNumber());

				nomineeDtos.add(nomineeDtoz);

			}

			dto.setNomineeDtos(nomineeDtos);

			propssalDto.add(dto);
		}

		return propssalDto;
	}

	
}

