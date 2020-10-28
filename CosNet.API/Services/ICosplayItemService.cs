using System;
using System.Collections.Generic;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.API.Services
{
    public interface ICosplayItemService
    {
        void CreateCosplayBoughtItem(CosplayBoughtItemForCreationDTO cosplayBoughtItem);
        void CreateCosplayMadeItem(CosplayMadeItemForCreationDTO cosplayMadeItem);
        void DeleteCosplayItem(Guid cosplayItemId);
        CosplayItemBaseDTO GetCosplayItem(Guid cosplayItemId);
        IEnumerable<CosplayItemBaseDTO> GetCosplayItems();
        void UpdateCosplayBoughtItem(Guid cosplayItemId, CosplayBoughtItemForUpdateDTO cosplayBoughtItem);
        void UpdateCosplayMadeItem(Guid cosplayItemId, CosplayMadeItemForUpdateDTO cosplayMadeItem);
    }
}