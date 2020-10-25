using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;

namespace CosNet.API.StartupSections.Configuration
{
    public static class ControllerConfiguration
    {
        public static IServiceCollection AddCosNetControllers(this IServiceCollection services)
        {
            services.AddControllers();
            services.AddMvc();

            return services;
        }
    }
}
