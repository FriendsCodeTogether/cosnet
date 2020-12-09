using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Services;
using CosNet.Shared.DTOs.CosplayNote;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace CosNet.API.Controllers
{
    [ApiController]
    [Route("Cosplay/{cosplayId}/[controller]")]
    public class CosplayNoteController : ControllerBase
    {
        private readonly ICosplayNoteService _cosplayNoteService;

        public CosplayNoteController(ICosplayNoteService cosplayNoteService)
        {
            _cosplayNoteService = cosplayNoteService;
        }

        /// <summary>
        /// Get all cosplays notes
        /// </summary>
        /// <param name="cosplayId">The id of the desired cosplay note</param>
        /// <returns>A list of cosplay notes</returns>
        [HttpGet]
        [Description("Get all cosplay notes")]
        [ProducesResponseType(typeof(List<CosplayNoteDTO>), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayNotes([FromRoute] Guid cosplayId)
        {
            return Ok(_cosplayNoteService.GetCosplayNotes(cosplayId));
        }

        /// <summary>
        /// Get a cosplay Note by id
        /// </summary>
        /// <param name="cosplayNoteId">The id of the desired cosplay note</param>
        /// <returns>a cosplay note</returns>
        [HttpGet("{cosplayNoteId}")]
        [Description("Get a cosplay note by id")]
        [ProducesResponseType(typeof(CosplayNoteDTO), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayNote([FromRoute] Guid cosplayNoteId)
        {
            return Ok(_cosplayNoteService.GetCosplayNote(cosplayNoteId));
        }

        /// <summary>
        /// Create cosplay note
        /// </summary>
        /// <param name="cosplayNote">The id of the desired cosplay note</param>
        [HttpPost]
        [Description("Create cosplay note")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult CreateCosplayNote([FromRoute] Guid cosplayId, [FromBody] CosplayNoteForCreationDTO cosplayNote)
        {
            cosplayNote.CosplayId = cosplayId;
            _cosplayNoteService.CreateCosplayNote(cosplayNote);
            return NoContent();
        }

        /// <summary>
        /// Update a cosplay note
        /// </summary>
        /// <param name="cosplayNoteId">The id of the desired cosplay note</param>
        /// <param name="cosplayNote">The desired cosplay note</param>
        [HttpPut("{cosplayNoteId}")]
        [Description("Update a cosplay note")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult UpdateCosplayNote([FromRoute] Guid cosplayNoteId, [FromBody] CosplayNoteForUpdateDTO cosplayNote)
        {
            _cosplayNoteService.UpdateCosplayNote(cosplayNoteId, cosplayNote);
            return NoContent();
        }

        /// <summary>
        /// Delete a cosplay
        /// </summary>
        /// <param name="cosplayNoteId">The id of the desired cosplay note</param>
        [HttpDelete("{cosplayNoteId}")]
        [Description("Delete a cosplay note")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult DeleteCosplayNote([FromRoute] Guid cosplayNoteId)
        {
            _cosplayNoteService.DeleteCosplayNote(cosplayNoteId);
            return NoContent();
        }
    }
}
