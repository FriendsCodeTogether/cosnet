using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Data.Repositories;
using CosNet.API.Entities;
using CosNet.API.Exceptions.Web;
using CosNet.Shared.DTOs.CosplayItemMaterial;

namespace CosNet.API.Services
{
    public class CosplayItemMaterialService : ICosplayItemMaterialService
    {
        private readonly IMapper _mapper;
        private readonly ICosplayItemRepository _cosplayItemRepository;
        private readonly ICosplayItemMaterialRepository _cosplayItemMaterialRepository;

        public CosplayItemMaterialService(IMapper mapper, ICosplayItemRepository cosplayItemRepository, ICosplayItemMaterialRepository cosplayItemMaterialRepository)
        {
            _mapper = mapper;
            _cosplayItemRepository = cosplayItemRepository;
            _cosplayItemMaterialRepository = cosplayItemMaterialRepository;
        }

        public IEnumerable<CosplayItemMaterialDTO> GetCosplayItemMaterials(Guid cosplayItemId)
        {
            if (!_cosplayItemRepository.CosplayItemExists(cosplayItemId))
            {
                throw new NotFoundException();
            }

            var cosplayItemMaterials = _cosplayItemMaterialRepository.GetCosplayItemMaterials(cosplayItemId);

            var cosplayItemMaterialDTOs = _mapper.Map<IEnumerable<CosplayItemMaterialDTO>>(cosplayItemMaterials);
            return cosplayItemMaterialDTOs;
        }

        public CosplayItemMaterialDTO GetCosplayItemMaterial(Guid cosplayItemMaterialId)
        {
            var cosplayItemMaterial = _cosplayItemMaterialRepository.GetCosplayItemMaterial(cosplayItemMaterialId);

            if (cosplayItemMaterial == null)
            {
                throw new NotFoundException();
            }

            var cosplayItemMaterialDTO = _mapper.Map<CosplayItemMaterialDTO>(cosplayItemMaterial);
            return cosplayItemMaterialDTO;
        }

        public void CreateCosplayItemMaterial(CosplayItemMaterialForCreationDTO cosplayItemMaterial)
        {
            var cosplayEntity = _mapper.Map<CosplayItemMaterial>(cosplayItemMaterial);
            _cosplayItemMaterialRepository.AddCosplayItemMaterial(cosplayEntity);
            _cosplayItemMaterialRepository.SaveChanges();
        }

        public void UpdateCosplayItemMaterial(Guid cosplayItemMaterialId, CosplayItemMaterialForUpdateDTO cosplayItemMaterial)
        {
            var existingCosplayItemMaterial = _cosplayItemMaterialRepository.GetCosplayItemMaterial(cosplayItemMaterialId);

            if (existingCosplayItemMaterial == null)
            {
                throw new NotFoundException();
            }

            // map the entity to a cosplayItemMaterialForUpdateDto
            // apply the updated field values to that dto
            // map the cosplayItemMaterialForUpdateDto back to an Entity
            _mapper.Map(cosplayItemMaterial, existingCosplayItemMaterial);

            _cosplayItemMaterialRepository.UpdateCosplayItemMaterial(existingCosplayItemMaterial);

            _cosplayItemMaterialRepository.SaveChanges();
        }

        public void DeleteCosplayItemMaterial(Guid cosplayItemMaterialId)
        {
            var cosplayItemMaterial = _cosplayItemMaterialRepository.GetCosplayItemMaterial(cosplayItemMaterialId);

            if (cosplayItemMaterial == null)
            {
                throw new NotFoundException();
            }

            _cosplayItemMaterialRepository.DeleteCosplayItemMaterial(cosplayItemMaterialId);
            _cosplayItemMaterialRepository.SaveChanges();
        }
    }
}
