using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace CosNet.API.Services
{
    public class UserService : IUserService
    {

        public ClaimsPrincipal User { get; }
        public Guid UserId => Guid.Parse(User.Claims.FirstOrDefault(c => c.Type == "sub")?.Value);

        public UserService(IHttpContextAccessor httpContextAccessor)
        {
            User = httpContextAccessor.HttpContext?.User;
        }
    }
}
