using System;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.API.Repositories;
using CosNet.Shared.ViewModels;
using Microsoft.AspNetCore.Mvc;

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
        [HttpGet("{id}")]
        public IActionResult GetCosplay(Guid id)
        {
            Cosplay cosplay = _cosplayRepository.GetCosplayById(id);
            var cosplayVM = _mapper.Map<CosplayVM>(cosplay);
            return Ok(cosplayVM);
        }

        // POST <CosplayController>
        [HttpPost]
        public IActionResult Post([FromBody] Cosplay cosplay)
        {
            _cosplayRepository.AddCosplay(cosplay);
            return NoContent();
        }

        // PUT <CosplayController>/5
        [HttpPut("{id}")]
        public IActionResult Put([FromRoute] Guid id, [FromBody] Cosplay cosplay)
        {
            _cosplayRepository.UpdateCosplay(cosplay);
            return NoContent();
        }

        // DELETE <CosplayController>/5
        [HttpDelete("{id}")]
        public IActionResult Delete(Guid id)
        {
            _cosplayRepository.DeleteCosplay(id);
            return NoContent();
        }
    }
}
