using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItemMaterial;

namespace CosNet.WebUI.Services
{
    public interface ICosplayItemMaterialService
    {
        Task<IEnumerable<CosplayItemMaterialDTO>> GetCosplayItemMaterialsAsync(Guid cosplayItemId);

        Task<CosplayItemMaterialDTO> GetCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId);

        Task CreateCosplayItemMaterialAsync(Guid cosplayItemId, CosplayItemMaterialForCreationDTO cosplayItemMaterial);

        Task UpdateCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId, CosplayItemMaterialForUpdateDTO cosplayItemMaterial);

        Task DeleteCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId);
    }
}
