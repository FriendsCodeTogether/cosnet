﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Services;
using CosNet.Shared.DTOs.CosplayItem;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace CosNet.API.Controllers
{
    [Route("Cosplay/{cosplayId}/[controller]")]
    [ApiController]
    public class CosplayItemController : ControllerBase
    {
        private readonly ICosplayItemService _cosplayItemService;

        public CosplayItemController(ICosplayItemService cosplayItemService)
        {
            _cosplayItemService = cosplayItemService;
        }

        /// <summary>
        /// Get all cosplays items
        /// </summary>
        /// <param name="cosplayId">The id of the desired cosplay item</param>
        /// <returns>A list of cosplay items</returns>
        [HttpGet]
        [Description("Get all cosplay items")]
        [ProducesResponseType(typeof(List<CosplayItemDTO>), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItems([FromRoute] Guid cosplayId)
        {
            return Ok(_cosplayItemService.GetCosplayItems(cosplayId));
        }

        /// <summary>
        /// Get a cosplay Item by id
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay item</param>
        /// <returns>a cosplay item</returns>
        [HttpGet("{cosplayItemId}")]
        [Description("Get a cosplay item by id")]
        [ProducesResponseType(typeof(CosplayItemDTO), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItem([FromRoute] Guid cosplayItemId)
        {
            return Ok(_cosplayItemService.GetCosplayItem(cosplayItemId));
        }

        /// <summary>
        /// Create cosplay item
        /// </summary>
        /// <param name="cosplayItem">The id of the desired cosplay item</param>
        [HttpPost]
        [Description("Create cosplay item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult CreateCosplayItem([FromRoute] Guid cosplayId, [FromBody] CosplayItemForCreationDTO cosplayItem)
        {
            cosplayItem.CosplayId = cosplayId;
            _cosplayItemService.CreateCosplayItem(cosplayItem);
            return NoContent();
        }

        /// <summary>
        /// Update a cosplay item
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay item</param>
        /// <param name="cosplayItem">The desired cosplay item</param>
        [HttpPut("{cosplayItemId}")]
        [Description("Update a cosplay item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult UpdateCosplayItem([FromRoute] Guid cosplayItemId, [FromBody] CosplayItemForUpdateDTO cosplayItem)
        {
            _cosplayItemService.UpdateCosplayItem(cosplayItemId, cosplayItem);
            return NoContent();
        }

        /// <summary>
        /// Delete a cosplay
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay item</param>
        [HttpDelete("{cosplayItemId}")]
        [Description("Delete a cosplay item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult DeleteCosplayItem([FromRoute] Guid cosplayItemId)
        {
            _cosplayItemService.DeleteCosplayItem(cosplayItemId);
            return NoContent();
        }
    }
}
