using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.DTOs.CosplayItemMaterial;

namespace CosNet.API.Profiles
{
    public class CosplayItemMaterialProfile : Profile
    {
        public CosplayItemMaterialProfile()
        {
            CreateMap<CosplayItemMaterial, CosplayItemMaterialDTO>().ReverseMap();
            CreateMap<CosplayItemMaterial, CosplayItemMaterialForCreationDTO>().ReverseMap();
            CreateMap<CosplayItemMaterial, CosplayItemMaterialForUpdateDTO>().ReverseMap();
        }
    }
}
