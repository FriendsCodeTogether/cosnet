using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Services;
using CosNet.Shared.DTOs.CosplayItemMaterial;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace CosNet.API.Controllers
{
    [Route("cosplay/{cosplayItemId}/[controller]")]
    [ApiController]
    public class CosplayItemMaterialController : ControllerBase
    {
        private readonly ICosplayItemMaterialService _cosplayItemMaterialService;

        public CosplayItemMaterialController(ICosplayItemMaterialService cosplayItemMaterialService)
        {
            _cosplayItemMaterialService = cosplayItemMaterialService;
        }

        /// <summary>
        /// Get all cosplays item materials
        /// </summary>
        /// <param name="cosplayItemId">The id of the desired cosplay item material</param>
        /// <returns>A list of cosplay item materials</returns>
        [HttpGet]
        [Description("Get all cosplay item materials")]
        [ProducesResponseType(typeof(List<CosplayItemMaterialDTO>), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItemMaterials([FromRoute] Guid cosplayItemId)
        {
            return Ok(_cosplayItemMaterialService.GetCosplayItemMaterials(cosplayItemId));
        }

        /// <summary>
        /// Get a cosplay Item by id
        /// </summary>
        /// <param name="cosplayItemMaterialId">The id of the desired cosplay item material</param>
        /// <returns>a cosplay item material</returns>
        [HttpGet("{cosplayItemId}")]
        [Description("Get a cosplay item material by id")]
        [ProducesResponseType(typeof(CosplayItemMaterialDTO), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItemMaterial([FromRoute] Guid cosplayItemMaterialId)
        {
            return Ok(_cosplayItemMaterialService.GetCosplayItemMaterial(cosplayItemMaterialId));
        }

        /// <summary>
        /// Create cosplay item material
        /// </summary>
        /// <param name="cosplayItemMaterial">The id of the desired cosplay item material</param>
        [HttpPost]
        [Description("Create cosplay item material")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult CreateCosplayItemMaterial([FromRoute] Guid cosplayItemId, [FromBody] CosplayItemMaterialForCreationDTO cosplayItemMaterial)
        {
            cosplayItemMaterial.CosplayItemId = cosplayItemId;
            _cosplayItemMaterialService.CreateCosplayItemMaterial(cosplayItemMaterial);
            return NoContent();
        }

        /// <summary>
        /// Update a cosplay item material
        /// </summary>
        /// <param name="cosplayItemMaterialId">The id of the desired cosplay item material</param>
        /// <param name="cosplayItemMaterial">The desired cosplay item material</param>
        [HttpPut("{cosplayItemMaterialId}")]
        [Description("Update a cosplay item")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult UpdateCosplayItemMaterial([FromRoute] Guid cosplayItemMaterialId, [FromBody] CosplayItemMaterialForUpdateDTO cosplayItemMaterial)
        {
            _cosplayItemMaterialService.UpdateCosplayItemMaterial(cosplayItemMaterialId, cosplayItemMaterial);
            return NoContent();
        }

        /// <summary>
        /// Delete a cosplay
        /// </summary>
        /// <param name="cosplayItemMaterialId">The id of the desired cosplay item</param>
        [HttpDelete("{cosplayItemMaterialId}")]
        [Description("Delete a cosplay item material")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult DeleteCosplayItem([FromRoute] Guid cosplayItemMaterialId)
        {
            _cosplayItemMaterialService.DeleteCosplayItemMaterial(cosplayItemMaterialId);
            return NoContent();
        }
    }
}
