using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Entities;
using CosNet.API.Repositories;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CosNet.API.Controllers
{
   [ApiController]
   [Route("[controller]")]
   public class CosplayController : ControllerBase
   {
      private readonly ICosplayRepository _cosplayRepository;

      public CosplayController(ICosplayRepository cosplayRepository)
      {
         _cosplayRepository = cosplayRepository;
      }

      // GET: <CosplayController>
      [HttpGet]
      public IEnumerable<Cosplay> Get()
      {
         IEnumerable<Cosplay> cosplays = _cosplayRepository.GetAllCosplays();
         return cosplays;
      }

      // GET <CosplayController>/5
      [HttpGet("{id}")]
      public string Get(int id)
      {
         return "value";
      }

      // POST <CosplayController>
      [HttpPost]
      public void Post([FromBody] string value)
      {
      }

      // PUT <CosplayController>/5
      [HttpPut("{id}")]
      public void Put(int id, [FromBody] string value)
      {
      }

      // DELETE <CosplayController>/5
      [HttpDelete("{id}")]
      public void Delete(int id)
      {
      }
   }
}
