using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.API.Profiles
{
    public class CosplayItemProfile : Profile
    {
        public CosplayItemProfile()
        {
            CreateMap<CosplayItemBase, CosplayItemBaseDTO>().ReverseMap();

            CreateMap<CosplayBoughtItem, CosplayBoughtItemDTO>().ReverseMap();
            CreateMap<CosplayBoughtItem, CosplayBoughtItemForCreationDTO>().ReverseMap();
            CreateMap<CosplayBoughtItem, CosplayBoughtItemForUpdateDTO>().ReverseMap();

            CreateMap<CosplayMadeItem, CosplayMadeItemDTO>().ReverseMap();
            CreateMap<CosplayMadeItem, CosplayMadeItemForCreationDTO>().ReverseMap();
            CreateMap<CosplayMadeItem, CosplayMadeItemForUpdateDTO>().ReverseMap();
        }
    }
}
