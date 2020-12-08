using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Data.Repositories;
using CosNet.API.Entities;
using CosNet.API.Exceptions.Web;
using CosNet.Shared.DTOs.CosplayNote;

namespace CosNet.API.Services
{
    public class CosplayNoteService : ICosplayNoteService
    {
        private readonly IMapper _mapper;
        private readonly ICosplayRepository _cosplayRepository;
        private readonly ICosplayNoteRepository _cosplayNoteRepository;

        public CosplayNoteService(IMapper mapper,ICosplayRepository cosplayRepository, ICosplayNoteRepository cosplayNoteRepository)
        {
            _mapper = mapper;
            _cosplayRepository = cosplayRepository;
            _cosplayNoteRepository = cosplayNoteRepository;
        }

        public IEnumerable<CosplayNoteDTO> GetCosplayNotes(Guid cosplayId)
        {
            if (!_cosplayRepository.CosplayExists(cosplayId))
            {
                throw new NotFoundException();
            }

            var cosplayNotes = _cosplayNoteRepository.GetCosplayNotes(cosplayId);
            var cosplayNoteDTOs = _mapper.Map<IEnumerable<CosplayNoteDTO>>(cosplayNotes);
            return cosplayNoteDTOs;
        }

        public CosplayNoteDTO GetCosplayNote(Guid cosplayNoteId)
        {
            var cosplayNote = _cosplayNoteRepository.GetCosplayNote(cosplayNoteId);

            if (cosplayNote == null)
            {
                throw new NotFoundException();
            }

            var cosplayNoteDTO = _mapper.Map<CosplayNoteDTO>(cosplayNote);
            return cosplayNoteDTO;
        }

        public void CreateCosplayNote(CosplayNoteForCreationDTO cosplayNote)
        {
            var cosplayNoteEntity = _mapper.Map<CosplayNote>(cosplayNote);
            _cosplayNoteRepository.AddCosplayNote(cosplayNoteEntity);
            _cosplayNoteRepository.SaveChanges();
        }

        public void UpdateCosplayNote(Guid cosplayNoteId, CosplayNoteForUpdateDTO cosplayNote)
        {
            var existingCosplayNote = _cosplayNoteRepository.GetCosplayNote(cosplayNoteId);

            if (existingCosplayNote == null)
            {
                throw new NotFoundException();
            }

            // map the entity to a cosplayForUpdateDto
            // apply the updated field values to that dto
            // map the cosplayForUpdateDto back to an Entity
            _mapper.Map(cosplayNote, existingCosplayNote);

            _cosplayNoteRepository.UpdateCosplayNote(existingCosplayNote);

            _cosplayNoteRepository.SaveChanges();
        }

        public void DeleteCosplayNote(Guid cosplayNoteId)
        {
            var cosplayNote = _cosplayNoteRepository.GetCosplayNote(cosplayNoteId);

            if (cosplayNote == null)
            {
                throw new NotFoundException();
            }

            _cosplayNoteRepository.DeleteCosplayNote(cosplayNoteId);
            _cosplayNoteRepository.SaveChanges();
        }
    }
}
