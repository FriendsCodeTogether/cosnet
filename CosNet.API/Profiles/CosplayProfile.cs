using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.ViewModels;

namespace CosNet.API.Profiles
{
   public class CosplayProfile : Profile
   {
      public CosplayProfile()
      {
         CreateMap<Cosplay, CosplayVM>().ReverseMap();
      }
   }
}
