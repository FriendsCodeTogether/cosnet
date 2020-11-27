using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace CosNet.API.Services
{
    public class UserService
    {
        private readonly ClaimsPrincipal _user;

        public UserService(HttpContextAccessor httpContextAccessor)
        {
            if (httpContextAccessor.HttpContext != null)
            {
                _user = httpContextAccessor.HttpContext.User;
            } 
        }
    }
}
