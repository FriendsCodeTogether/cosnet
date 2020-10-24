using System;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.API.Data.Repositories;
using CosNet.Shared.DTOs;
using Microsoft.AspNetCore.Mvc;
using CosNet.API.Services;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CosNet.API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CosplayController : ControllerBase
    {
        private readonly CosplayService _cosplayService;

        public CosplayController(CosplayService cosplayService)
        {
            _cosplayService = cosplayService;
        }

        // GET: <CosplayController>
        [HttpGet]
        public IActionResult GetAll()
        {
            return Ok(_cosplayService.GetCosplays());
        }

        // GET <CosplayController>/5
        [HttpGet("{cosplayId}")]
        public IActionResult GetCosplay(Guid cosplayId)
        {
            return Ok(_cosplayService.GetCosplay(cosplayId));
        }

        // POST <CosplayController>
        [HttpPost]
        public IActionResult CreateCosplay([FromBody] CosplayForCreationDTO cosplay)
        {
            _cosplayService.CreateCosplay(cosplay);
            return NoContent();
        }

        // PUT <CosplayController>/5
        [HttpPut("{cosplayId}")]
        public IActionResult UpdateCosplay([FromRoute] Guid cosplayId, [FromBody] CosplayForUpdateDTO cosplay)
        {
            _cosplayService.UpdateCosplay(cosplayId, cosplay);
            return NoContent();
        }

        // DELETE <CosplayController>/5
        [HttpDelete("{cosplayId}")]
        public IActionResult DeleteCosplay(Guid cosplayId)
        {
            _cosplayService.DeleteCosplay(cosplayId);
            return NoContent();
        }
    }
}
