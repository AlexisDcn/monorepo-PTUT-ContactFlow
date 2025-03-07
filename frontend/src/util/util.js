export default async function doAjaxRequest(url, options) {
  const response = await fetch(url, options);
  const result = await response.json();
  if (!response.ok) throw result;
  return result;
}
