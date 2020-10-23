using System;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.API.Repositories;
using CosNet.Shared.DTOs;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore.Diagnostics;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CosNet.API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CosplayController : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly ICosplayRepository _cosplayRepository;

        public CosplayController(IMapper mapper, ICosplayRepository cosplayRepository)
        {
            _mapper = mapper;
            _cosplayRepository = cosplayRepository;
        }

        // GET: <CosplayController>
        [HttpGet]
        public IActionResult GetAll()
        {
            return Ok(_cosplayRepository.GetAllCosplays());
        }

        // GET <CosplayController>/5
        [HttpGet("{cosplayId}")]
        public IActionResult GetCosplay(Guid cosplayId)
        {
            Cosplay cosplay = _cosplayRepository.GetCosplayById(cosplayId);
            var cosplayVM = _mapper.Map<CosplayDTO>(cosplay);
            return Ok(cosplayVM);
        }

        // POST <CosplayController>
        [HttpPost]
        public IActionResult Post([FromBody] CosplayForCreationDTO cosplay)
        {
            var cosplayEntity = _mapper.Map<Cosplay>(cosplay);
            _cosplayRepository.AddCosplay(cosplayEntity);
            _cosplayRepository.SaveChanges();
            return NoContent();
        }

        // PUT <CosplayController>/5
        [HttpPut("{cosplayId}")]
        public IActionResult Put([FromRoute] Guid cosplayId, [FromBody] CosplayForUpdateDTO cosplay)
        {
            var existingCosplay = _cosplayRepository.GetCosplayById(cosplayId);

            // map the entity to a courseForUpdateDto
            // apply the updated field values to that dto
            // map the courseForUpdateDto back to an Entity
            _mapper.Map(cosplay, existingCosplay);

            _cosplayRepository.UpdateCosplay(existingCosplay);

            _cosplayRepository.SaveChanges();

            return NoContent();
        }

        // DELETE <CosplayController>/5
        [HttpDelete("{cosplayId}")]
        public IActionResult Delete(Guid cosplayId)
        {
            _cosplayRepository.DeleteCosplay(cosplayId);
            return NoContent();
        }
    }
}
