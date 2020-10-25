using System;
using AutoMapper;
using CosNet.API.StartupSections.Configuration;
using CosNet.API.StartupSections.Middleware;
using CosNet.API.StartupSections.Middleware.Extentions;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace CosNet.API
{
    public class Startup
    {
        public IConfiguration Configuration { get; }

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddCosNetControllers();

            services.AddCosNetCorsPolicy(Configuration);

            services.AddCosNetDbContexts(Configuration);

            services.AddCosNetSwagger();

            services.AddCosNetAuthentication(Configuration);

            services.AddAutoMapper(AppDomain.CurrentDomain.GetAssemblies());

            services.AddCosNetRepositories();

            services.AddCosNetServices();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseCosNetSwagger();

            app.UseRouting();

            app.UseCosNetCorsPolicy();

            app.UseAuthentication();

            app.UseAuthorization();

            app.UseExceptionMiddleware();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
