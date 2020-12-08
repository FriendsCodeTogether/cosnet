using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Data.Repositories;
using CosNet.API.Entities;
using CosNet.API.Exceptions.Web;
using CosNet.Shared.DTOs.CosplayItemNote;

namespace CosNet.API.Services
{
    public class CosplayItemNoteService : ICosplayItemNoteService
    {
        private readonly IMapper _mapper;
        private readonly ICosplayItemRepository _cosplayItemRepository;
        private readonly ICosplayItemNoteRepository _cosplayItemNoteRepository;

        public CosplayItemNoteService(IMapper mapper, ICosplayItemRepository cosplayItemRepository, ICosplayItemNoteRepository cosplayItemNoteRepository)
        {
            _mapper = mapper;
            _cosplayItemRepository = cosplayItemRepository;
            _cosplayItemNoteRepository = cosplayItemNoteRepository;
        }

        public IEnumerable<CosplayItemNoteDTO> GetCosplayItemNotes(Guid cosplayItemId)
        {
            if (!_cosplayItemRepository.CosplayItemExists(cosplayItemId))
            {
                throw new NotFoundException();
            }

            var cosplayItemNotes = _cosplayItemNoteRepository.GetCosplayItemNotes(cosplayItemId);
            var cosplayItemNoteDTOs = _mapper.Map<IEnumerable<CosplayItemNoteDTO>>(cosplayItemNotes);
            return cosplayItemNoteDTOs;
        }

        public CosplayItemNoteDTO GetCosplayItemNote(Guid cosplayItemNoteId)
        {
            var cosplayItemNote = _cosplayItemNoteRepository.GetCosplayItemNote(cosplayItemNoteId);

            if (cosplayItemNote == null)
            {
                throw new NotFoundException();
            }

            var cosplayItemNoteDTO = _mapper.Map<CosplayItemNoteDTO>(cosplayItemNote);
            return cosplayItemNoteDTO;
        }

        public void CreateCosplayItemNote(CosplayItemNoteForCreationDTO cosplayItemNote)
        {
            var cosplayItemNoteEntity = _mapper.Map<CosplayItemNote>(cosplayItemNote);
            _cosplayItemNoteRepository.AddCosplayItemNote(cosplayItemNoteEntity);
            _cosplayItemNoteRepository.SaveChanges();
        }

        public void UpdateCosplayItemNote(Guid cosplayItemNoteId, CosplayItemNoteForUpdateDTO cosplayItemNote)
        {
            var existingCosplayItemNote = _cosplayItemNoteRepository.GetCosplayItemNote(cosplayItemNoteId);

            if (existingCosplayItemNote == null)
            {
                throw new NotFoundException();
            }

            // map the entity to a cosplayForUpdateDto
            // apply the updated field values to that dto
            // map the cosplayForUpdateDto back to an Entity
            _mapper.Map(cosplayItemNote, existingCosplayItemNote);

            _cosplayItemNoteRepository.UpdateCosplayItemNote(existingCosplayItemNote);

            _cosplayItemNoteRepository.SaveChanges();
        }

        public void DeleteCosplayItemNote(Guid cosplayItemNoteId)
        {
            var cosplayItemNote = _cosplayItemNoteRepository.GetCosplayItemNote(cosplayItemNoteId);

            if (cosplayItemNote == null)
            {
                throw new NotFoundException();
            }

            _cosplayItemNoteRepository.DeleteCosplayItemNote(cosplayItemNoteId);
            _cosplayItemNoteRepository.SaveChanges();
        }
    }
}
