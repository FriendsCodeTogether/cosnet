using System;
using System.Collections.Generic;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.API.Services
{
    public interface ICosplayItemService
    {
        void CreateCosplayItem(CosplayItemForCreationDTO cosplayItem);
        void DeleteCosplayItem(Guid cosplayItemId);
        CosplayItemDTO GetCosplayItem(Guid cosplayItemId);
        IEnumerable<CosplayItemDTO> GetCosplayItems(Guid cosplayId);
        void UpdateCosplayItem(Guid cosplayItemId, CosplayItemForUpdateDTO cosplayItem);
    }
}
