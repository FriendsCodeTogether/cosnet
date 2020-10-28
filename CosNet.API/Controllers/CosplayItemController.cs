using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Services;
using CosNet.Shared.DTOs.CosplayItem;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CosNet.API.Controllers
{
    [Route("[controller]")]
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
        /// <returns>A list of cosplay items</returns>
        [HttpGet]
        [Description("Get all cosplay items")]
        [ProducesResponseType(typeof(List<CosplayItemBaseDTO>), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItems()
        {
            return Ok(_cosplayItemService.GetCosplayItems());
        }

        /// <summary>
        /// Get a cosplay Item by id
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay item</param>
        /// <returns>a cosplay</returns>
        [HttpGet("{cosplayItemId}")]
        [Description("Get a cosplay item by id")]
        [ProducesResponseType(typeof(CosplayItemBaseDTO), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItem([FromRoute] Guid cosplayItemId)
        {
            return Ok(_cosplayItemService.GetCosplayItem(cosplayItemId));
        }

        /// <summary>
        /// Create cosplay bought item
        /// </summary>
        [HttpPost("bought")]
        [Description("Create cosplay bought item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult CreateCosplayBoughtItem([FromBody] CosplayBoughtItemForCreationDTO cosplayBoughtItem)
        {
            _cosplayItemService.CreateCosplayBoughtItem(cosplayBoughtItem);
            return NoContent();
        }

        /// <summary>
        /// Create cosplay made item
        /// </summary>
        [HttpPost("made")]
        [Description("Create cosplay made item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult CreateCosplayMadeItem([FromBody] CosplayMadeItemForCreationDTO cosplayMadeItem)
        {
            _cosplayItemService.CreateCosplayMadeItem(cosplayMadeItem);
            return NoContent();
        }

        /// <summary>
        /// Update a cosplay bought item
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay bought item</param>
        [HttpPut("bought/{cosplayItemId}")]
        [Description("Update a cosplay bought item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult UpdateCosplayBoughtItem([FromRoute] Guid cosplayItemId, [FromBody] CosplayBoughtItemForUpdateDTO cosplayBoughtItem)
        {
            _cosplayItemService.UpdateCosplayBoughtItem(cosplayItemId, cosplayBoughtItem);
            return NoContent();
        }

        /// <summary>
        /// Update a cosplay made item
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay made item</param>
        [HttpPut("made/{cosplayItemId}")]
        [Description("Update a cosplay made item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult UpdateCosplayMadeItem([FromRoute] Guid cosplayItemId, [FromBody] CosplayMadeItemForUpdateDTO cosplayMadeItem)
        {
            _cosplayItemService.UpdateCosplayMadeItem(cosplayItemId, cosplayMadeItem);
            return NoContent();
        }

        /// <summary>
        /// Delete a cosplay
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay</param>
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
