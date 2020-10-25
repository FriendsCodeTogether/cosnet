using System;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.API.Data.Repositories;
using CosNet.Shared.DTOs;
using Microsoft.AspNetCore.Mvc;
using CosNet.API.Services;
using System.ComponentModel;
using Microsoft.AspNetCore.Http;
using System.Collections.Generic;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CosNet.API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CosplayController : ControllerBase
    {
        private readonly ICosplayService _cosplayService;

        public CosplayController(ICosplayService cosplayService)
        {
            _cosplayService = cosplayService;
        }

        /// <summary>
        /// Get all cosplays
        /// </summary>
        /// <returns>A list of cosplays</returns>
        [HttpGet]
        [Description("Get all cosplays")]
        [ProducesResponseType(typeof(List<CosplayDTO>), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplays()
        {
            return Ok(_cosplayService.GetCosplays());
        }

        /// <summary>
        /// Get a cosplay by id
        /// </summary>
        /// <returns>a cosplay</returns>
        [HttpGet("{cosplayId}")]
        [Description("Get a cosplay by id")]
        [ProducesResponseType(typeof(CosplayDTO), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplay([FromRoute] Guid cosplayId)
        {
            return Ok(_cosplayService.GetCosplay(cosplayId));
        }

        /// <summary>
        /// Create cosplay
        /// </summary>
        [HttpPost]
        [Description("Create cosplay")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult CreateCosplay([FromBody] CosplayForCreationDTO cosplay)
        {
            _cosplayService.CreateCosplay(cosplay);
            return NoContent();
        }

        /// <summary>
        /// Update a cosplay
        /// </summary>
        [HttpPut("{cosplayId}")]
        [Description("Update a cosplay")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult UpdateCosplay([FromRoute] Guid cosplayId, [FromBody] CosplayForUpdateDTO cosplay)
        {
            _cosplayService.UpdateCosplay(cosplayId, cosplay);
            return NoContent();
        }

        /// <summary>
        /// Delete a cosplay
        /// </summary>
        [HttpDelete("{cosplayId}")]
        [Description("Delete a cosplay")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult DeleteCosplay([FromRoute] Guid cosplayId)
        {
            _cosplayService.DeleteCosplay(cosplayId);
            return NoContent();
        }
    }
}
