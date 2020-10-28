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
        private readonly ICosplayItemRepository _cosplayItemRepository;

        public CosplayItemService(IMapper mapper, ICosplayItemRepository cosplayItemRepository)
        {
            _mapper = mapper;
            _cosplayItemRepository = cosplayItemRepository;
        }

        public IEnumerable<CosplayItemBaseDTO> GetCosplayItems()
        {
            var cosplayItems = _cosplayItemRepository.GetCosplayItems();
            var cosplayItemDTOs = _mapper.Map<IEnumerable<CosplayItemBaseDTO>>(cosplayItems);
            return cosplayItemDTOs;
        }

        public CosplayItemBaseDTO GetCosplayItem(Guid cosplayItemId)
        {
            var cosplayItem = _cosplayItemRepository.GetCosplayItem(cosplayItemId);

            if (cosplayItem == null)
            {
                throw new NotFoundException();
            }

            var cosplayItemDTO = _mapper.Map<CosplayItemBaseDTO>(cosplayItem);
            return cosplayItemDTO;
        }

        public void CreateCosplayBoughtItem(CosplayBoughtItemForCreationDTO cosplayBoughtItem)
        {
            var cosplayBoughtEntity = _mapper.Map<CosplayItemBase>(cosplayBoughtItem);
            _cosplayItemRepository.AddCosplayItem(cosplayBoughtEntity);
            _cosplayItemRepository.SaveChanges();
        }

        public void CreateCosplayMadeItem(CosplayMadeItemForCreationDTO cosplayMadeItem)
        {
            var cosplayMadeEntity = _mapper.Map<CosplayItemBase>(cosplayMadeItem);
            _cosplayItemRepository.AddCosplayItem(cosplayMadeEntity);
            _cosplayItemRepository.SaveChanges();
        }

        public void UpdateCosplayBoughtItem(Guid cosplayItemId, CosplayBoughtItemForUpdateDTO cosplayBoughtItem)
        {
            var existingCosplayItem = _cosplayItemRepository.GetCosplayItem(cosplayItemId);

            if (existingCosplayItem == null)
            {
                throw new NotFoundException();
            }

            // map the entity to a cosplayItemForUpdateDto
            // apply the updated field values to that dto
            // map the cosplayItemForUpdateDto back to an Entity
            _mapper.Map(cosplayBoughtItem, existingCosplayItem);

            _cosplayItemRepository.UpdateCosplayItem(existingCosplayItem);

            _cosplayItemRepository.SaveChanges();
        }

        public void UpdateCosplayMadeItem(Guid cosplayItemId, CosplayMadeItemForUpdateDTO cosplayMadeItem)
        {
            var existingCosplayItem = _cosplayItemRepository.GetCosplayItem(cosplayItemId);

            if (existingCosplayItem == null)
            {
                throw new NotFoundException();
            }

            // map the entity to a cosplayItemForUpdateDto
            // apply the updated field values to that dto
            // map the cosplayItemForUpdateDto back to an Entity
            _mapper.Map(cosplayMadeItem, existingCosplayItem);

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
