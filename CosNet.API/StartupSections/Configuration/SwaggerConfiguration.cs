using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.OpenApi.Models;

namespace CosNet.API.StartupSections.Configuration
{
    public static class SwaggerConfiguration
    {
        public static IServiceCollection AddCosNetSwagger(this IServiceCollection services)
        {
            return services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "CosNet API", Version = "v1" });
            });
        }
    }
}
