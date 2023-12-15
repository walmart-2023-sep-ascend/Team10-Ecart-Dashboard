module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://localhost:9200',
      changeOrigin: true,
    })
  );
};
