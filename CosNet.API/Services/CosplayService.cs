using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Data.Repositories;
using CosNet.API.Entities;
using CosNet.API.Exceptions.Web;
using CosNet.Shared.DTOs.Cosplay;

namespace CosNet.API.Services
{
    public class CosplayService : ICosplayService
    {
        private readonly IMapper _mapper;
        private readonly ICosplayRepository _cosplayRepository;

        public CosplayService(IMapper mapper, ICosplayRepository cosplayRepository)
        {
            _mapper = mapper;
            _cosplayRepository = cosplayRepository;
        }

        public IEnumerable<CosplayDTO> GetCosplays()
        {
            var cosplays = _cosplayRepository.GetCosplays();
            var cosplayDTOs = _mapper.Map<IEnumerable<CosplayDTO>>(cosplays);
            return cosplayDTOs;
        }

        public CosplayDTO GetCosplay(Guid cosplayId)
        {
            var cosplay = _cosplayRepository.GetCosplay(cosplayId);

            if (cosplay == null)
            {
                throw new NotFoundException();
            }

            var cosplayDTO = _mapper.Map<CosplayDTO>(cosplay);
            return cosplayDTO;
        }

        public void CreateCosplay(CosplayForCreationDTO cosplay)
        {
            var cosplayEntity = _mapper.Map<Cosplay>(cosplay);
            _cosplayRepository.AddCosplay(cosplayEntity);
            _cosplayRepository.SaveChanges();
        }

        public void UpdateCosplay(Guid cosplayId, CosplayForUpdateDTO cosplay)
        {
            var existingCosplay = _cosplayRepository.GetCosplay(cosplayId);

            if (existingCosplay == null)
            {
                throw new NotFoundException();
            }

            // map the entity to a cosplayForUpdateDto
            // apply the updated field values to that dto
            // map the cosplayForUpdateDto back to an Entity
            _mapper.Map(cosplay, existingCosplay);

            _cosplayRepository.UpdateCosplay(existingCosplay);

            _cosplayRepository.SaveChanges();
        }

        public void DeleteCosplay(Guid cosplayId)
        {
            var cosplay = _cosplayRepository.GetCosplay(cosplayId);

            if (cosplay == null)
            {
                throw new NotFoundException();
            }

            _cosplayRepository.DeleteCosplay(cosplayId);
            _cosplayRepository.SaveChanges();
        }
    }
}
