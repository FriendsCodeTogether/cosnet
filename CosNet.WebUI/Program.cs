using System;
using System.Net.Http;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Text;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.DependencyInjection.Extensions;
using Microsoft.AspNetCore.Components.WebAssembly.Authentication;
using CosNet.WebUI.Services;

namespace CosNet.WebUI
{
   public class Program
   {
      public static async Task Main(string[] args)
      {
         var builder = WebAssemblyHostBuilder.CreateDefault(args);
         builder.RootComponents.Add<App>("app");

         builder.Services.AddHttpClient("api",
               client => { client.BaseAddress = new Uri(builder.Configuration["CosNetAPIUrl"]); })
            .AddHttpMessageHandler(sp =>
            {
               var handler = sp.GetService<AuthorizationMessageHandler>()
                  .ConfigureHandler(
                      authorizedUrls: new[] { builder.Configuration["CosNetAPIUrl"] },
                      scopes: new[] { "cosnet-api" });

               return handler;
            });

         builder.Services.AddScoped(sp => sp.GetService<IHttpClientFactory>().CreateClient("api"));

         builder.Services.AddScoped<ICosplayService, CosplayService>();
         builder.Services.AddScoped<ICosplayItemService, CosplayItemService>();
         builder.Services.AddScoped<ICosplayItemMaterialService, CosplayItemMaterialService>();

         builder.Services.AddOidcAuthentication(options =>
         {
            builder.Configuration.Bind("oidc", options.ProviderOptions);
         });

         await builder.Build().RunAsync();
      }
   }
}
