using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Services;
using CosNet.Shared.DTOs.CosplayItemNote;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace CosNet.API.Controllers
{
    [ApiController]
    [Route("Cosplay/{cosplayId}/[controller]")]
    public class CosplayItemNoteController : ControllerBase
    {
        private readonly ICosplayItemNoteService _cosplayItemNoteService;

        public CosplayItemNoteController(ICosplayItemNoteService cosplayItemNoteService)
        {
            _cosplayItemNoteService = cosplayItemNoteService;
        }

        /// <summary>
        /// Get all cosplays notes
        /// </summary>
        /// <param name="cosplayId">The id of the desired cosplay note</param>
        /// <returns>A list of cosplay notes</returns>
        [HttpGet]
        [Description("Get all cosplay notes")]
        [ProducesResponseType(typeof(List<CosplayItemNoteDTO>), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItemNotes([FromRoute] Guid cosplayId)
        {
            return Ok(_cosplayItemNoteService.GetCosplayItemNotes(cosplayId));
        }

        /// <summary>
        /// Get a cosplay Note by id
        /// </summary>
        /// <param name="cosplayItemNoteId">The id of the desired cosplay note</param>
        /// <returns>a cosplay note</returns>
        [HttpGet("{cosplayItemNoteId}")]
        [Description("Get a cosplay note by id")]
        [ProducesResponseType(typeof(CosplayItemNoteDTO), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult GetCosplayItemNote([FromRoute] Guid cosplayItemNoteId)
        {
            return Ok(_cosplayItemNoteService.GetCosplayItemNote(cosplayItemNoteId));
        }

        /// <summary>
        /// Create cosplay note
        /// </summary>
        /// <param name="cosplayItemNote">The id of the desired cosplay note</param>
        [HttpPost]
        [Description("Create cosplay note")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult CreateCosplayItemNote([FromRoute] Guid cosplayId, [FromBody] CosplayItemNoteForCreationDTO cosplayItemNote)
        {
            cosplayItemNote.CosplayItemId = cosplayId;
            _cosplayItemNoteService.CreateCosplayItemNote(cosplayItemNote);
            return NoContent();
        }

        /// <summary>
        /// Update a cosplay note
        /// </summary>
        /// <param name="cosplayItemNoteId">The id of the desired cosplay note</param>
        /// <param name="cosplayItemNote">The desired cosplay note</param>
        [HttpPut("{cosplayItemNoteId}")]
        [Description("Update a cosplay note")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult UpdateCosplayItemNote([FromRoute] Guid cosplayItemNoteId, [FromBody] CosplayItemNoteForUpdateDTO cosplayItemNote)
        {
            _cosplayItemNoteService.UpdateCosplayItemNote(cosplayItemNoteId, cosplayItemNote);
            return NoContent();
        }

        /// <summary>
        /// Delete a cosplay
        /// </summary>
        /// <param name="cosplayItemNoteId">The id of the desired cosplay note</param>
        [HttpDelete("{cosplayItemNoteId}")]
        [Description("Delete a cosplay note")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status401Unauthorized)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public IActionResult DeleteCosplayItemNote([FromRoute] Guid cosplayItemNoteId)
        {
            _cosplayItemNoteService.DeleteCosplayItemNote(cosplayItemNoteId);
            return NoContent();
        }
    }
}
