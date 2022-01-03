package br.com.asv.ms.client;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.fge.jsonpatch.JsonPatch;

import br.com.asv.base.client.dto.IBaseDto;
import br.com.asv.base.client.ws.IResponse;
import br.com.asv.base.client.ws.Response;

public interface IWSClient<D extends IBaseDto<I>,I extends Serializable> {

	@GetMapping
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> findAll() ;
	
	@GetMapping(path = "/search")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> findAll(@RequestParam(value = "search", required = false) String search) ;
	
	@GetMapping(path = "/{id}")
	@ResponseBody
	ResponseEntity<Response<D, ?, String>> findOne(@PathVariable("id") I id);
	
	@GetMapping(path = "/page")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> findAll(Pageable pageable);
	
	@GetMapping(path = "/page/search")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> findAll(@RequestParam(value = "search", required = false) String search, Pageable pageable);
	
	@GetMapping(path = "/page/{status}")
	@ResponseBody
	ResponseEntity<IResponse> findAllPage(@PathVariable("status") String status, Pageable pageable);
	
	@GetMapping(path = "/status/{status}")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> findAllStatus(@PathVariable("status") String status);
	
	@GetMapping(path = "/enabled")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> findAllEnabled();
	
	@GetMapping(path = "/enabled/page")
	@ResponseBody
	ResponseEntity<IResponse> findAllEnabled(Pageable pageable);
	
	@GetMapping(path = "/disabled")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> findAllDisabled();
	
	@GetMapping(path = "/disabled/page")
	@ResponseBody
	ResponseEntity<IResponse> findAllDisabled( Pageable pageable);
	
	@GetMapping(path = "/count")
	@ResponseBody 
	ResponseEntity<IResponse> countAll();
	
	@GetMapping(path = "/count/search")
	@ResponseBody 
	ResponseEntity<IResponse> countAll(@RequestParam(value = "search", required = false) String search);
	
	@PostMapping(path = "/collection")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> save(@RequestBody Collection<D> collection);
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<Response<D, ?, String>> save( @RequestBody  D dto ) ;
	
	@DeleteMapping(path = "/collection/disabled")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> delete(@RequestBody  Collection<D> collection);
	
	@DeleteMapping(path = "/disabled/{id}")
	@ResponseBody
	ResponseEntity<Response<D, ?, String>> delete(@PathVariable("id") I id);
	
	@DeleteMapping(path = "/remove/{id}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<Response<D, ?, String>> remove(@PathVariable("id") I id);

	@DeleteMapping(path = "/remove")
	@ResponseBody
	ResponseEntity<Response<D, ?, String>> remove(@RequestBody Collection<D> collection);
	
	@PatchMapping(path = "/collection/enabled")
	@ResponseBody
	ResponseEntity<Response<List<D>, ?, String>> recovery(@RequestBody  Collection<D> collection);
	
	@PatchMapping(path = "/enabled/{id}")
	@ResponseBody
	ResponseEntity<Response<D, ?, String>> recovery(@PathVariable("id") I id);
	
	@PutMapping
	ResponseEntity<Response<D, ?, String>> update( @RequestBody  D dto);
	
	@PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
	ResponseEntity<Response<D, ?, String>> patchDto(@PathVariable("id") I id, @RequestBody JsonPatch patchDto);
}
