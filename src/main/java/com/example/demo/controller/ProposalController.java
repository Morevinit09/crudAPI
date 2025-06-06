package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NomineeDto;
import com.example.demo.dto.ProposalDto;
import com.example.demo.entity.Proposal;
import com.example.demo.response.Response;
import com.example.demo.service.ProposalService;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

	@Autowired
	private ProposalService proposalService;

	@PostMapping("/add")
	public Response addProposal(@RequestBody ProposalDto proposalDto) {

		Response response = new Response();

		try {
			Proposal proposal = proposalService.addProposal(proposalDto);

			response.setData(proposal);
			response.setMessage("Success");
			response.setStatus(true);

		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("failed");
			response.setStatus(false);
		}

		return response;
	}

	@PatchMapping("/delete/{proposalId}")
	public Response deleteproposal(@PathVariable Integer proposalId) {
		Response response = new Response();

		try {

			String deleteproposal = proposalService.deleteproposal(proposalId);
			response.setData(deleteproposal);
			response.setMessage("Success");
			response.setStatus(true);

		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("failed");
			response.setStatus(false);
		}

		return response;

	}

	@PutMapping("/update/{proposalId}")
	public Response updatepropsal(@PathVariable Integer proposalId, @RequestBody ProposalDto proposalDto) {
		Response response = new Response();

		try {

			String updatepropsal = proposalService.updatepropsal(proposalId, proposalDto);
			response.setData(updatepropsal);
			response.setMessage("Success");
			response.setStatus(true);

		} catch (IllegalArgumentException e) {
			response.setData(new ArrayList<>());
			response.setMessage(e.getMessage());
			response.setStatus(false);
		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("failed");
			response.setStatus(false);
		}

		return response;

	}

	@GetMapping("/list-allproposal")
	public Response getAllProposalWithNominee() {
		Response response = new Response();

		try {
			List<ProposalDto> allProposalWithNominee = proposalService.getAllProposalWithNominee();

			response.setData(allProposalWithNominee);
			response.setMessage("Success");
			response.setStatus(true);

		} catch (IllegalArgumentException e) {
			response.setData(new ArrayList<>());
			response.setMessage(e.getMessage());
			response.setStatus(false);
		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("failed");
			response.setStatus(false);
		}
		return response;

	}

	@PutMapping("/nominee_update_byid/{nomineeId}")
	public Response updateNominee(@PathVariable Integer nomineeId, @RequestBody NomineeDto nomineeDto) {
		Response response = new Response();

		try {

			Response updateNominee = proposalService.updateNominee(nomineeId, nomineeDto);
			response.setData(updateNominee);
			response.setMessage("Success");
			response.setStatus(true);

		} catch (IllegalArgumentException e) {
			response.setData(new ArrayList<>());
			response.setMessage(e.getMessage());
			response.setStatus(false);
		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("failed");
			response.setStatus(false);
		}

		return response;

	}

}
