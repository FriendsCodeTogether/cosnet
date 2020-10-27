using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.DTOs.Cosplay;

namespace CosNet.API.Profiles
{
    public class CosplayProfile : Profile
    {
        public CosplayProfile()
        {
            CreateMap<Cosplay, CosplayDTO>().ReverseMap();
            CreateMap<Cosplay, CosplayForCreationDTO>().ReverseMap();
            CreateMap<Cosplay, CosplayForUpdateDTO>().ReverseMap();
        }
    }
}
