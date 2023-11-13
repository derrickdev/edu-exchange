/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  transpilePackages: ["lucide-react"],
  basePath: "",
  compress: true,
  cleanDistDir: true,
  optimizeFonts: true,
};

module.exports = nextConfig;
