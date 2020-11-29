Try {
  Write-Output "Checking dotnet ef tools version."
  $command = "dotnet ef --version"
  Invoke-Command $command -ErrorAction Stop
}
Catch {
  Write-Output "Dotnet ef tools are not installed."
  Write-Output "Installing dotnet ef tools."
  dotnet tool install -g dotnet-ef --version 5.0.0
}
Finally {
  Write-Output "Checking dotnet ef tools version."
  dotnet ef --version
}

Write-Output "Migrating CosNet IDP DbContexts:"

Write-Output "Migrating ApplicationDbContext"
dotnet ef database update -c ApplicationDbContext

Write-Output "Migrating PersistedGrantDbContext"
dotnet ef database update -c PersistedGrantDbContext

Write-Output "Migrating ConfigurationDbContext"
dotnet ef database update -c ConfigurationDbContext

Write-Output "Seeding CosNet IDP DbContexts:"
dotnet run /seed
