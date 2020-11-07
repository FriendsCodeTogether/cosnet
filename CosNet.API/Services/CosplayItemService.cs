using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Data.Repositories;
using CosNet.API.Entities;
using CosNet.API.Exceptions.Web;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.API.Services
{
    public class CosplayItemService : ICosplayItemService
    {
        private readonly IMapper _mapper;
        private readonly ICosplayRepository _cosplayRepository;
        private readonly ICosplayItemRepository _cosplayItemRepository;

        public CosplayItemService(IMapper mapper, ICosplayRepository cosplayRepository, ICosplayItemRepository cosplayItemRepository)
        {
            _mapper = mapper;
            _cosplayRepository = cosplayRepository;
            _cosplayItemRepository = cosplayItemRepository;
        }

        public IEnumerable<CosplayItemDTO> GetCosplayItems(Guid cosplayId)
        {
            if (!_cosplayRepository.CosplayExists(cosplayId))
            {
                throw new NotFoundException();
            }

            var cosplayItems = _cosplayItemRepository.GetCosplayItems(cosplayId);

            var cosplayItemDTOs = _mapper.Map<IEnumerable<CosplayItemDTO>>(cosplayItems);
            return cosplayItemDTOs;
        }

        public CosplayItemDTO GetCosplayItem(Guid cosplayItemId)
        {
            var cosplayItem = _cosplayItemRepository.GetCosplayItem(cosplayItemId);

            if (cosplayItem == null)
            {
                throw new NotFoundException();
            }

            var cosplayItemDTO = _mapper.Map<CosplayItemDTO>(cosplayItem);
            return cosplayItemDTO;
        }

        public void CreateCosplayItem(CosplayItemForCreationDTO cosplayItem)
        {
            var cosplayEntity = _mapper.Map<CosplayItem>(cosplayItem);
            _cosplayItemRepository.AddCosplayItem(cosplayEntity);
            _cosplayItemRepository.SaveChanges();
        }

        public void UpdateCosplayItem(Guid cosplayItemId, CosplayItemForUpdateDTO cosplayItem)
        {
            var existingCosplayItem = _cosplayItemRepository.GetCosplayItem(cosplayItemId);

            if (existingCosplayItem == null)
            {
                throw new NotFoundException();
            }

            // map the entity to a cosplayItemForUpdateDto
            // apply the updated field values to that dto
            // map the cosplayItemForUpdateDto back to an Entity
            _mapper.Map(cosplayItem, existingCosplayItem);

            _cosplayItemRepository.UpdateCosplayItem(existingCosplayItem);

            _cosplayItemRepository.SaveChanges();
        }

        public void DeleteCosplayItem(Guid cosplayItemId)
        {
            var cosplayItem = _cosplayItemRepository.GetCosplayItem(cosplayItemId);

            if (cosplayItem == null)
            {
                throw new NotFoundException();
            }

            _cosplayItemRepository.DeleteCosplayItem(cosplayItemId);
            _cosplayItemRepository.SaveChanges();
        }
    }
}
