using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Data.Repositories;
using Microsoft.Extensions.DependencyInjection;

namespace CosNet.API.StartupSections.Configuration
{
    public static class RepositoryConfiguration
    {
        public static IServiceCollection AddCosNetRepositories(this IServiceCollection services)
        {
            services.AddTransient<ICosplayRepository, CosplayRepository>();
            services.AddTransient<ICosplayItemRepository, CosplayItemRepository>();
            services.AddTransient<ICosplayItemMaterialRepository, CosplayItemMaterialRepository>();

            return services;
        }
    }
}
